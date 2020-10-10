input/Opportunity.java      : Add an example with the OPEN/CLOSE on the same line

Note, the 1 in the file name is meant to mark points in time.  In reality, Opportunity1.java is the first version, Opportunity2.java is the second version of output/Opportunity.java

output/Opportunity1.java    : The output of the file if it were run for the first time, or if it were re-run and Opportunity1.java were not modified
output/Opportunity2.java    : Add in PREOPEN:validateLeadStatusCondition and POSTCLOSE:validateLeadStatusCondition
                              The system copies in the json and sets the sha1 to override
output/Opportunity3.java    : Replace the entire validateLeadStatusCondition node
