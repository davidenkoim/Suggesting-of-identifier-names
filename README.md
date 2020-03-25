# Suggesting of identifier names

### Предложение названия переменных.

- 2014 [Learning Natural Coding Conventions](https://www.researchgate.net/profile/Earl_Barr/publication/260250447_Learning_Natural_Coding_Conventions/links/0c96053abd4a775a80000000/Learning-Natural-Coding-Conventions.pdf)

`NATURALIZE`: используются n-граммы.

- 2014 [On the Localness of Software](http://zptu.net/papers/fse2014_localness.pdf)

N-граммы + кэш. Вероятности предложения того или иного токена определяется взвешенной суммой вероятности от N-грамм и вероятности по кэшу.

`Top1 accuracy = 61.5%`

- 2017 [Are Deep Neural Networks the Best Choice for Modeling Source Code?](https://vhellendoorn.github.io/PDF/fse2017.pdf)

[GitHub с имплементацией](https://github.com/SLP-team/SLP-Core/tree/master/FSE'17%20Replication)

Здесь тоже используются N-граммы с кэшем, но запоминаются не вероятности отдельных N-граммов, а просто их количество, плюс используются все файлы, которые есть в проекте. Также используется *Jelinek-Mercer smoothing*, алгоритм которого изложен на 5 стр.

В статье приведено сравнение с другими методами сглаживания и LSTM. Оказывается, что их модель работает лучше и быстрее DL аналогов. При этом N-граммы можно прикольно дополнять LSTM'ом, если вдруг N-грам выдает неизвестный токен. На сколько я понял, это улучшает энропию, но не *accuracy*.

`Top1 accuracy = 75.9%`

- 2018 [LEARNING TO REPRESENT PROGRAMS WITH GRAPHS](https://arxiv.org/pdf/1711.00740.pdf)

[GitHub с имплементацией](https://github.com/Microsoft/gated-graph-neural-network-samples)

В этой статье авторы строят граф по коду и запихивают его в GGNN. Тренируют сеть заменяя определенную переменную на токен `<SLOT>`.

Минусы подхода в том, что не очень хорошие результаты на новых проектах, скорее всего, из-за того, что словарь переменных и классовое дерево в новых проектах может сильно отличаться от таковых в обучающих проектах.

Идея прикольная но `не SOTA`.

`VarNaming accuracy = 44.%`

`VarMisuse accuracy = 78.2%`

На данный момент, это все, что нашел по предложению имен переменных. 
