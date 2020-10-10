input/Opportunity.java      : This is a very basic file with a single dynamic section and a postopen / preclose

Note, the 1 in the file name is meant to mark points in time.  In reality, Opportunity1.java is the first version, Opportunity2.java is the second version of output/Opportunity.java

output/Opportunity1.java    : The output of the file if it were run for the first time, or if it were re-run and Opportunity1.java were not modified
output/Opportunity2.java    : Imagine I modified output/Opportunity1.java to add AnotherInterface and reran the script.  
                              The tool should pick up the change, because the sha1 in Opportunity2.java and the text do not match.
                              It should then rewrite output/Opportunity2.java based on input/Opportunity.java EXCEPT it should keep my modification of POSTOPEN:CLASS
output/Opportunity2.java    : Imagine I modified output/Opportunity2.java to add a comment down the bottom and reran the script.  
                              The tool should pick up the change, because the sha1 in Opportunity3.java and the text do not match.
                              It should then rewrite output/Opportunity3.java based on input/Opportunity.java EXCEPT it should keep my modifications in POSTOPEN:CLASS and PRECLOSE:CLASS

