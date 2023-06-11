using Microsoft.AspNetCore.Mvc;

namespace Clinic.Areas.Admin.Controllers
{
    public class DoctorController : Controller
    {
        public IActionResult Index()
        {
            return View();
        }
    }
}
