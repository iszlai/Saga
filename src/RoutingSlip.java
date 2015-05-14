import java.awt.IllegalComponentStateException;
import java.util.Deque;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;


public class RoutingSlip {

	public RoutingSlip(WorkItem[] workItems) {
		// TODO Auto-generated constructor stub
	}
	        final Deque<WorkLog> completedWorkLogs = new ConcurrentLinkedDeque<WorkLog>();
	        final Queue<WorkItem> nextWorkItem = new ConcurrentLinkedQueue<WorkItem>();
	     
	        public RoutingSlip()
	        {
	        }
	     
	        public RoutingSlip(Iterable<WorkItem> workItems)
	        {
	            for (WorkItem workItem : workItems)
	            {
	                this.nextWorkItem.add(workItem);
	            }
	        }
	     
	        public boolean isCompleted()
	        {
	           // get { return this.nextWorkItem.Count == 0; }
	        	return nextWorkItem.isEmpty();
	        }
	     
	        public boolean isInProgress()
	        {
	           // get { return this.completedWorkLogs.Count > 0; }
	        	return !nextWorkItem.isEmpty();
	        	
	        }
	     
	        public boolean ProcessNext()
	        {
	            if (this.isCompleted())
	            {
	                throw new IllegalComponentStateException("trying to process empty tasklist");
	            }
	     
	            WorkItem currentItem = this.nextWorkItem.remove();
	            Activity activity=null;// = (Activity)Activator.CreateInstance(currentItem.ActivityType);
	            try
	            {
	                WorkLog result = activity.doWork(currentItem);
	                if (result != null)
	                {
	                    this.completedWorkLogs.add(result);
	                    return true;
	                }
	            }
	            catch (Exception e)
	            {
	                //Console.WriteLine("Exception {0}", e.Message);
	            	e.printStackTrace();
	            }
	            return false;
	        }
	     
	        public Uri ProgressUri()
	        {
	          //  get
	           // {
	                if (isCompleted())
	                {
	                    return null;
	                }
	                else
	                {
	                    return
	                        this.nextWorkItem.peek().getprog();
	                }
	            //}
	        }
	     
	        public Uri CompensationUri()
	        {
	            //get
	            //{
	                if (!isInProgress())
	                {
	                    return null;
	                }
	                else
	                {
	                    return this.completedWorkLogs.peek().getcom();
	                       // ((Activity)Activator.CreateInstance(this.completedWorkLogs.Peek().ActivityType)).
	                       //     CompensationQueueAddress;
	                    			
	                     
	                }
	            //}
	        }
	     
	        public boolean UndoLast()
	        {
	            if (!this.isInProgress())
	            {
	                throw new IllegalComponentStateException();
	            }
	     
	            WorkLog currentItem = this.completedWorkLogs.pop();
	            Activity activity = null;//(Activity)Activator.CreateInstance(currentItem.ActivityType);
	            try
	            {
	                return activity.compensate(currentItem, this);
	            }
	           catch (Exception e)
	           {
	               e.printStackTrace();
	              // throw;
	           }
				return false;
	    
	       }
}
