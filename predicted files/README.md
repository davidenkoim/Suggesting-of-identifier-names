# Structure

In the begining of all files you can see some statistics about it:

```
// Path id: 24
// Path to file: C:\Users\Igor\IdeaProjects\SLP-Core-0.3\java-projects\cassandra-trunk\src\java\org\apache\cassandra\auth\AuthenticatedUser.java
// Number of identifiers: 57	Accuracy: 38.60%	MRR: 43.63%
```

Then you can see how predictions are shown:
```
// True 	Rank of true in predictions : [(predicted token, probability of a token), ...] or [predictions, ...]
```

Here you can see an example:
```
private final RoleResource role;
// role                 1	: [('grantee', 0.6304732992467197), ('role', 0.19784792937911685), ('resource', 0.006906719363549505), ('ROLE_B_3', 0.006270424355758373), ('ROLE_C_2', 0.006270408050792376), ('ROLE_C_1', 0.006270408050792376), ('ROLE_C_3', 0.006270391745826379), ('ROOT_RESOURCE', 0.006270359135894386), ('key', 2.8894281507469616e-05), ('put', 2.6725721029872377e-05)]

    public AuthenticatedUser(String name)
// name                 0	: [('name', 0.051862465351503434), ('keyspace', 0.047576457660164044), ('keyspaceName', 0.02328181100870658), ('query', 0.016895033872806182), ('ksName', 0.011472841254951904), ('s', 0.009216603594709107), ('value', 0.008199687543519407), ('msg', 0.007973830321312613), ('key', 0.007599354342927692), ('str', 0.006485373852936805)]
```
