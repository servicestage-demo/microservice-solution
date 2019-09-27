using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using Microsoft.Extensions.Options;
using order.Config;
using order.ViewModel;

namespace order.Controllers.v1
{
    [Route("v1/orders")]
    public class OrdersController : Controller
    {
        private readonly AppSettings AppSettings;

        private readonly ILogger<OrdersController> logger;
        public OrdersController(IOptions<AppSettings> options, ILogger<OrdersController> logger) : base()
        {
            AppSettings = options.Value;
        }
        [HttpGet]
        public IActionResult Get([FromQuery]int pageSize = 10, [FromQuery]int pageIndex = 0)
        {
            var ep = AppSettings.ServiceEndpoints.PaymentServiceEndpoint;
            WebRequest payRequest = WebRequest.Create(ep+"/v1/payments");
            payRequest.Method = "GET";
            var encoding = Encoding.UTF8;
            var resp = payRequest.GetResponse();
            StreamReader reader = new StreamReader(resp.GetResponseStream(), encoding);
            var responseData = reader.ReadToEnd();
            return Ok(responseData);
        }

        // GET api/values/5
        [HttpGet("{id:int}")]
        public IActionResult GetById(int id)
        {
            return Ok($"value {id}");
        }

        // POST api/values
        [HttpPost]
        public IActionResult InsertOrUpdate([FromBody]string value)
        {
            if (value == null)
            {
                return NotFound();
            }

            return Ok(value);
        }

        // PUT api/values/5
        [HttpPut("{id}")]
        public IActionResult Update(int id, [FromBody]string value)
        {
            if (value == null)
            {
                return NotFound();
            }

            return Ok(value);
        }

        // DELETE api/values/5
        [HttpDelete("{id:int}")]
        public IActionResult Delete(int id)
        {
            return Ok($"{id}");
        }
    }
}
