using Clinic.DataAccess.Repository.IRepository;
using Clinic.Models;
using Clinic.Models.ViewModels;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Clinic.Areas.Admin.Controllers
{
    [Area("Admin")]
    public class CityController : Controller
    {
        private readonly IUnitOfWork _unitOfWork;

        public CityController(IUnitOfWork unitOfWork)
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
            IEnumerable<CountryOrRegion> countryOrRegionList = await _unitOfWork.CountryOrRegion.GetAllAsync();
            CityVM cityVM = new CityVM()
            {
                City = new City(),
                CountryOrRegionList = countryOrRegionList.Select(i => new SelectListItem
                {
                    Text = i.Name,
                    Value = i.Id.ToString()
                })
            };
            if (id == null)
            {
                // create
                return View(cityVM);
            }

            // edit
            cityVM.City = await _unitOfWork.City.GetAsync(id.GetValueOrDefault());
            if (cityVM.City == null)
            {
                return NotFound();
            }
            return View(cityVM);
        }

        // in MVC we can do api calls within the controllers and view actions
        #region API_CALS

        [HttpGet]
        public async Task<IActionResult> GetAll()
        {
            var allObj = await _unitOfWork.City.GetAllAsync(includeProperties: "CountryOrRegion");
            return Json(new { data = allObj });
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Upsert(CityVM cityVM)
        {
            if (ModelState.IsValid) // make sure validation is valid if client side is not working
            {
                if (cityVM.City.Id == 0)
                {
                    await _unitOfWork.City.AddAsync(cityVM.City);
                }
                else
                {
                    _unitOfWork.City.Update(cityVM.City);
                }
                await _unitOfWork.SaveAsync();
                return RedirectToAction(nameof(Index));
            }
            return View(cityVM);
        }

        [HttpDelete]
        public async Task<IActionResult> Delete(int id)
        {
            var objFromDb = await _unitOfWork.City.GetAsync(id);
            if (objFromDb == null)
            {
                return Json(new { success = false, message = "Error while deleting" });
            }
            await _unitOfWork.City.RemoveAsync(objFromDb);
            await _unitOfWork.SaveAsync();
            return Json(new { success = true, message = "Delete Successful" });
        }

        #endregion
    }
}
