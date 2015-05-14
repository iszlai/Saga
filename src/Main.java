
public class Main {
	  private static final String Send = null;
	static ActivityHost[] processes;

	public static void main(String[] args) {
		
		RoutingSlip routingSlip = new RoutingSlip(new WorkItem[]
				           {
				               new WorkItem<ReserveCarActivity>(new WorkItemArguments("vehicleType", "Compact")),
				               new WorkItem<ReserveHotelActivity>(new WorkItemArguments("roomType", "Suite")),
				               new WorkItem<ReserveFlightActivity>(new WorkItemArguments("destination", "DUS"))
				           });

	
//		  processes = new ActivityHost[]
//				                            {
//				                                new ActivityHost<ReserveCarActivity>(Send),
//				                                new ActivityHost<ReserveHotelActivity>(Send),
//				                                new ActivityHost<ReserveFlightActivity>(Send)
//				                            };
	
	
	
	}

}
