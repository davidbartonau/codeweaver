package oneit.website.orm;

import java.util.*;
import oneit.automessage.orm.*;
import oneit.logging.*;
import oneit.objstore.*;
import oneit.utils.*;
import oneit.emails.orm.EmailMessage;
import oneit.security.SecUser;
import oneit.tasktracker.orm.TTUserExtension;
import oneit.utils.math.NullArith;
import oneit.utils.parsers.FieldException;
import oneit.utils.transform.param.*;


/* {OPEN:CLASS} { a: 1, b: 2 } */
public class Opportunity extends BaseOpportunity implements AutoEmailable, TransformRegistrar
{
/* {POSTOPEN:CLASS} { a: 1, b: 2 } */
    TaskType        STATUS_CHANGED          = TaskType.forValue("STATUS_CHANGED");

    
    // This constructor should not be called
    public Opportunity ()
    {
        // Do not add any code to this, always put it in initialiseNewObject
    }

    @Override
    public void validate(ValidationContext context)
    {
        super.validate(context); 
        
        if (getLeadStatus() == null)
        {
            ContactStatus status = getContactStatusFor(CONTACT_STATUS_NOT_SET);
            try
            {
                setLeadStatus(status);
            }
            catch (FieldException ex)
            {        
                throw new NestedException(ex);
            }
        }
    }

    
    @Override
    public void preCommit(boolean willBeStored) throws Exception
    {
        super.preCommit(willBeStored); //To change body of generated methods, choose Tools | Templates.
    }
    

    @Override
    protected void postLeadStatusChange() throws FieldException
    {

        Activity    newRecord   =   Activity.createActivity(getTransaction());
        SecUser     currentUser =   SecUser.getTXUser(getTransaction(), "admin");
     
        String   description = "Status Changed to ";

        if( getLeadStatus() != null)
        {
            description = description + getLeadStatus().getDescription();
        }
        else
        {
            description = description + "None";
        }
        
        newRecord.setTaskType(STATUS_CHANGED);
        newRecord.setDescription(description);
        newRecord.setContact(getContact());
        newRecord.setPerson(currentUser);
        
        oneit.automessage.orm.AutomessageThread.setupMessageThreads(getContact());
        oneit.automessage.orm.AutomessageThread.setupMessageThreads(this);
    }

    
    public void createInitialActivity() throws StorageException, FieldException
    {
        Activity            newActivity             =   Activity.createActivity(getTransaction());
        TaskType            tasktypeForInitialCall  =   TaskType.forName("STATUS_CHANGED");
        ContactStatus       leadStatus              =   ContactStatus.forValue("NOT_SET");
        
        String              description             =   "Status Changed to ";
        
        if (leadStatus != null)
        {
            description =   description + leadStatus.getDescription();
        }
        else
        {
            description =   description + "None";
        }
        
        newActivity.setDescription(description);
        newActivity.setPerson(SecUser.getTXUser(getTransaction()));
        newActivity.setContact(getContact());
        newActivity.setTaskType(tasktypeForInitialCall);
        newActivity.setCompletedDate(new Date());     
    }

    
    @Override
    public void registerTransforms()
    {
        MasterTransform.create(TRANSFORM_TYPE_AUTO_MSG)
                        .add("customer", FranchiseContact.class)
                        .add("opportunity", Opportunity.class)
                        .add("am", SecUser.class)
                        .add("amext", TTUserExtension.class)
                        .register();
    }
/* {PRECLOSE:CLASS} { a: 1, b: 2 } */    
}
/* {CLOSE:CLASS} { a: 1, b: 2 } */
