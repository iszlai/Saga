
public interface Activity {
	public WorkLog doWork(WorkItem workItem);
	public boolean compensate(WorkLog item, RoutingSlip routingSlip);
	public  Uri CompensationQueueAddress();
	public  Uri WorkItemQueueAddress();
}
