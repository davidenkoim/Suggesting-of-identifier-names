# Types of raining:

* `selfTestingIdentifier`: train on the whole project, forget usages of identifier which we are currently predicting.
* `selfTesting`: train on the whole project, forget identifiers' usages of the file, then predict them.
* `selfTraining`: learn a file, predict the file(as in `selfTestingIdentifier`), forget the file, and so on...

# Projects' statistics

### elasticsearch-master
File name                                    |N identifiers|Accuracy  |  MRR  |
---------------------------------------------|-------------|----------|-------|
TransportGetAutoFollowPatternAction.java     |17           |0.8235    |0.8603 |
ActionListener.java                          |59           |0.5593    |0.6416 |
SizeMappingIT.java                           |16           |0.5625    |0.6458 |
SimpleChecksAdapter.java                     |14           |0.5714    |0.5893 |
AttachmentProcessorTests.java                |54           |0.7037    |0.7575 |
TransportAddVotingConfigExclusionsAction.java|37           |0.6757    |0.7032 |
MissingAggregator.java                       |13           |0.7692    |0.8654 |
DataCounts.java                              |17           |0.8824    |0.9314 |
ValidateMappingRequestPluginIT.java          |16           |0.9375    |0.9688 |
SingleOrdinalsTests.java                     |14           |0.4286    |0.5786 |
### cassandra-trunk
File name                                    |N identifiers|Accuracy  |  MRR  |
---------------------------------------------|-------------|----------|-------|
TombstoneHistogram.java                      |19           |0.4211    |0.5500 |
AbstractCommitLogServiceTest.java            |36           |0.8056    |0.8333 |
Connection.java                              |106          |0.6887    |0.7472 |
LoaderOptions.java                           |57           |0.5439    |0.5927 |
MethodComparator.java                        |23           |0.3478    |0.4638 |
AbstractPatriciaTrie.java                    |84           |0.5952    |0.6966 |
TriggerExecutor.java                         |39           |0.3846    |0.4701 |
BatchlogResponseHandler.java                 |10           |0.7000    |0.7500 |
DynamicEndpointSnitchTest.java               |18           |0.4444    |0.6005 |
LZ4Compressor.java                           |14           |0.5000    |0.7024 |
### xmlgraphics-batik-trunk
File name                                    |N identifiers|Accuracy  |  MRR  |
---------------------------------------------|-------------|----------|-------|
DOMUIEvent.java                              |17           |0.8824    |0.8941 |
SVGUserAgentAdapter.java                     |29           |1.0000    |1.0000 |
BaselineShiftManager.java                    |18           |0.7222    |0.7639 |
ScriptingEnvironment.java                    |138          |0.7246    |0.8019 |
FontFace.java                                |51           |0.7059    |0.7827 |
SVGGVTFont.java                              |139          |0.7122    |0.7528 |
SVGFontUtilities.java                        |48           |0.3958    |0.4781 |
TileRable8Bit.java                           |106          |0.4811    |0.5374 |
BridgeContext.java                           |244          |0.6475    |0.7292 |
ExternalResourcesTest.java                   |47           |0.7021    |0.7580 |
### ant-master
File name                                    |N identifiers|Accuracy  |  MRR  |
---------------------------------------------|-------------|----------|-------|
PropertyFileTest.java                        |19           |0.4211    |0.5707 |
JDBCTask.java                                |29           |0.5517    |0.6015 |
MailMessageTest.java                         |58           |0.8276    |0.8506 |
ZipEncodingTest.java                         |10           |0.2000    |0.3394 |
CVSPass.java                                 |12           |0.3333    |0.4861 |
JUnitLauncherTask.java                       |61           |0.4754    |0.5390 |
ModifiedSelector.java                        |77           |0.3377    |0.4111 |
MockBuildListener.java                       |12           |0.7500    |0.7619 |
XmlProperty.java                             |69           |0.4058    |0.5307 |
XSLTProcess.java                             |122          |0.4426    |0.5338 |

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
