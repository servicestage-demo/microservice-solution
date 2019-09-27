using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using Microsoft.Extensions.Options;
using payment.ViewModel;

namespace payment.Controllers.v1
{
    [Route("v1/payments")]
    public class PaymentsController : Controller
    {

        [HttpGet]
        public IActionResult Get([FromQuery]int pageSize = 10, [FromQuery]int pageIndex = 0)
        {
            var items = new List<string>
            {
              "100","101"
            };

            var total = items.Count;

            List<string> itemsOnPage;
            if (pageSize * pageIndex + pageSize > total)
            {
                itemsOnPage = items.GetRange(pageSize * pageIndex, total - pageSize * pageIndex);
            }
            else
            {
                itemsOnPage = items.GetRange(pageSize * pageIndex, pageSize);
            }
            
            var model = new PaginatedItemsViewModel<string>(
                pageIndex, pageSize, total, itemsOnPage
            );

            return Ok(model);
        }
        
    }
}
