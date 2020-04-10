import os
from javalang.tokenizer import tokenize, JavaToken
import re
import pickle
from collections import namedtuple

LexError = namedtuple('LexError', ['path', 'error'])


class TokenToSymbol:
    def __init__(self, voc_length=None, use_old_vocabulary=True):
        self.voc_length = voc_length
        self.token_to_symbol = {}
        if use_old_vocabulary and os.path.exists('_vocabulary.pickle'):
            with open('_vocabulary.pickle', 'rb') as f:
                self.token_to_symbol = pickle.load(f)
        self.symbol_to_token = {v: k for k, v in self.token_to_symbol.items()}

    def __setitem__(self, key, value):
        if self.voc_length and len(self) >= self.voc_length:
            return
        self.token_to_symbol[key] = value
        self.symbol_to_token[value] = key

    def __getitem__(self, token):
        return self.token_to_symbol[token.value if isinstance(token, JavaToken) else token]

    def __call__(self, symbol):
        return self.symbol_to_token[chr(symbol) if isinstance(symbol, int) else symbol]

    def add_token(self, token):
        if self.voc_length and len(self) >= self.voc_length:
            return
        v = token.value if isinstance(token, JavaToken) else token
        if v in self:
            return
        self[v] = chr(len(self))

    def __contains__(self, item):
        return item in self.token_to_symbol

    def add_tokens(self, values):
        if self.voc_length and len(self) >= self.voc_length:
            return
        for value in values:
            self.add_token(value)

    def update_vocabulary_cash(self):
        with open('_vocabulary.pickle', 'wb') as f:
            pickle.dump(self.token_to_symbol, f)

    def __len__(self):
        return len(self.token_to_symbol)

    def __str__(self):
        return str(self.token_to_symbol)


class Tokenizer:
    def __init__(self, path, voc_length=None, use_cashed_vocabulary=True, use_cashed_tokens=True):
        self.path = os.path.normpath(path)
        self.voc = TokenToSymbol(voc_length, use_cashed_vocabulary)
        self.tokens = {}
        if use_cashed_tokens and os.path.exists('_tokens.pickle'):
            with open('_tokens.pickle', 'rb') as f:
                self.tokens = pickle.load(f)
        self.errors = []

    def get_tokens(self, use_cash=True, update_vocabulary=True,
                   show_status=None):
        pattern = r".*.java$"
        if os.path.isfile(self.path) and re.match(pattern, self.path) and self.path not in self.tokens:
            self.tokens[os.path.normpath(self.path)] = self.cash(self.path, use_cash, update_vocabulary,
                                                                 ignore_errors=False)
        else:
            for root, _, files in os.walk(self.path):
                for file in files:
                    fpath = os.path.normpath(os.path.join(root, file))
                    if re.match(pattern, file) and fpath not in self.tokens:
                        ts = self.cash(fpath, use_cash, update_vocabulary, ignore_errors=True)
                        if ts is not None:
                            self.tokens[fpath] = ts
                        if show_status == 'full':
                            print('Done:' if ts is not None else 'Error:', fpath)
                if show_status == 'root':
                    print('Done:', root)
        if update_vocabulary:
            self.voc.update_vocabulary_cash()
        self.cash_tokens()
        return self.tokens

    def cash(self, fpath, use_cash=True, update_vocavulary=True, ignore_errors=False):
        cash_path = os.path.join('.tokens', fpath.replace('.java', '.pickle'))
        try:
            if use_cash and os.path.exists(cash_path):
                with open(cash_path, 'rb') as f:
                    ts = pickle.load(f)
                    if update_vocavulary:
                        self.voc.add_tokens(ts)
            else:
                with open(fpath, 'r') as f:
                    ts = list(tokenize(f.read()))
                    if update_vocavulary:
                        self.voc.add_tokens(ts)
                dirname = os.path.dirname(cash_path)
                if not os.path.exists(dirname):
                    os.makedirs(dirname)
                with open(cash_path, 'wb') as f:
                    pickle.dump(ts, f)
        except Exception as e:
            if ignore_errors:
                self.errors.append(LexError(fpath, e))
                return
            raise e
        return ts

    def cash_tokens(self):
        with open('_tokens.pickle', 'wb') as f:
            pickle.dump(self.tokens, f)

    def __call__(self, path):
        return self.tokens[os.path.normpath(path)]


if __name__ == '__main__':
    t = Tokenizer('java-projects')
    print(t('java-projects/ant-master/src/etc/testcases/core/containersrc/test/SpecialSeq.java'))
