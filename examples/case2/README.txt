input/Opportunity.java      : Add a little more sophistication / more tags

Note, the 1 in the file name is meant to mark points in time.  In reality, Opportunity1.java is the first version, Opportunity2.java is the second version of output/Opportunity.java

output/Opportunity1.java    : The output of the file if it were run for the first time, or if it were re-run and Opportunity1.java were not modified
output/Opportunity2.java    : In this case I added the POSTCLOSE:MasterTransformAdd with custom code.
                              When I manually added the POSTCLOSE, there would not be a sha1, and probably no json either
                              The tool should pick up that the POSTCLOSE is new / it has no sha1 and that therefore it is a custom node
                              It should then rewrite output/Opportunity2.java based on input/Opportunity.java EXCEPT it should keep my modification of POSTCLOSE:MasterTransformAdd content
                              The POSTCLOSE:MasterTransformAdd should then have the json copied from the POSTCLOSE and the sha1 set to override
output/Opportunity3.java    : I now take Opportunity2.java and modify it, adding PREOPEN:CLASS and POSTOPEN:MasterTransformAdd
                              In the same way as the previous one, my new nodes are preserved (as well as the POSTCLOSE:MasterTransformAdd from the previous stage)
output/Opportunity4.java    : In this final version, we override the content inside OPEN:preCommit
                              The program should recognise this and keep the customised content while rewriting the file.
                              All previous changes are also included.
