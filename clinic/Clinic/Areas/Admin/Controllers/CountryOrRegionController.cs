using Clinic.DataAccess.Repository.IRepository;
using Clinic.Models;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;

namespace Clinic.Areas.Admin.Controllers
{
    [Area("Admin")]
    public class CountryOrRegionController : Controller
    {
        private readonly IUnitOfWork _unitOfWork;

        public CountryOrRegionController(IUnitOfWork unitOfWork)
        {
            _unitOfWork = unitOfWork;
        }

        public IActionResult Index()
        {
            return View();
        }

        // update and insert; id == null ? insert : update
        public async Task<IActionResult> Upsert(int? id)
        {
            CountryOrRegion countryOrRegion = new CountryOrRegion();
            if (id == null)
            {
                // create
                return View(countryOrRegion);
            }

            // edit
            countryOrRegion = await _unitOfWork.CountryOrRegion.GetAsync(id.GetValueOrDefault());
            if (countryOrRegion == null)
            {
                return NotFound();
            }
            return View(countryOrRegion);
        }

        // in MVC we can do api calls within the controllers and view actions
        #region API_CALS

        [HttpGet]
        public async Task<IActionResult> GetAll()
        {
            var allObj = await _unitOfWork.CountryOrRegion.GetAllAsync();
            return Json(new { data = allObj });
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Upsert(CountryOrRegion countryOrRegion)
        {
            if (ModelState.IsValid) // make sure validation is valid if client side is not working
            {
                countryOrRegion.AlphaCode = countryOrRegion.AlphaCode.ToUpper();
                countryOrRegion.Name = countryOrRegion.Name.ToUpperInvariant();
                var objFromDb = await _unitOfWork.CountryOrRegion.GetAsync(countryOrRegion.Id);
                if (objFromDb == null)
                {
                    await _unitOfWork.CountryOrRegion.AddAsync(countryOrRegion);
                }
                else
                {
                    _unitOfWork.CountryOrRegion.Update(countryOrRegion);
                }
                await _unitOfWork.SaveAsync();
                return RedirectToAction(nameof(Index));
            }
            return View(countryOrRegion);
        }

        [HttpDelete]
        public async Task<IActionResult> Delete(int id)
        {
            var objFromDb = await _unitOfWork.CountryOrRegion.GetAsync(id);
            if (objFromDb == null)
            {
                return Json(new { success = false, message = "Error while deleting" });
            }
            await _unitOfWork.CountryOrRegion.RemoveAsync(objFromDb);
            await _unitOfWork.SaveAsync();
            return Json(new { success = true, message = "Delete Successful" });
        }

        #endregion
    }
}
