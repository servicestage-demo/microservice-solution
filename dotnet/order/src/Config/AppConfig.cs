namespace order.Config
{
    public class AppSettings
    {
        public ServiceEndpoints ServiceEndpoints { get; set; }
    }

    public class ServiceEndpoints {
        public string PaymentServiceEndpoint {get; set; } 
    }
}