
public abstract class ActivityHost<T> {

	public ActivityHost(String send) {
		// TODO Auto-generated constructor stub
	}
		Action<Uri, RoutingSlip> send;
	     
	        public ActivityHost(Action<Uri, RoutingSlip> send)
	        {
	            this.send = send;
	        }
	     
	        public void ProcessForwardMessage(RoutingSlip routingSlip)
	        {
	            if (!routingSlip.isCompleted())
	            {
	                // if the current step is successful, proceed
	                // otherwise go to the Unwind path
	                if (routingSlip.ProcessNext())
	                {
	                    // recursion stands for passing context via message
	                    // the routing slip can be fully serialized and passed
	                    // between systems. 
	                    this.send(routingSlip.ProgressUri(), routingSlip);
	                }
	                else
	                {
	                    // pass message to unwind message route
	                    this.send(routingSlip.CompensationUri(), routingSlip);
	                }
	            }
	        }
	     
	        public void ProcessBackwardMessage(RoutingSlip routingSlip)
	        {
	            if (routingSlip.isInProgress())
	            {
	                // UndoLast can put new work on the routing slip
	                // and return false to go back on the forward 
	                // path
	                if (routingSlip.UndoLast())
	                {
	                    // recursion stands for passing context via message
	                    // the routing slip can be fully serialized and passed
	                    // between systems 
	                    this.send(routingSlip.CompensationUri(), routingSlip);
	                }
	                else
	                {
	                    this.send(routingSlip.ProgressUri(), routingSlip);
	                }
	            }
	        }
	     
	        private void send(Uri progressUri, RoutingSlip routingSlip) {
				// TODO Auto-generated method stub
				
			}

			public abstract boolean AcceptMessage(Uri uri, RoutingSlip routingSlip);
	    }

