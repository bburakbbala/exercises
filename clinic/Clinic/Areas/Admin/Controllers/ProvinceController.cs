using Clinic.DataAccess.Data;
using Clinic.DataAccess.Repository.IRepository;
using Clinic.Models;
using Clinic.Models.ViewModels;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Clinic.Areas.Admin.Controllers
{
    [Area("Admin")]
    public class ProvinceController : Controller
    {
        private readonly IUnitOfWork _unitOfWork;
        private readonly ApplicationDbContext _db;

        public ProvinceController(IUnitOfWork unitOfWork, ApplicationDbContext db)
        {
            _unitOfWork = unitOfWork;
            _db = db;
        }

        public IActionResult Index()
        {
            return View();
        }

        public async Task<IActionResult> Upsert(int? id)
        {
            IEnumerable<CountryOrRegion> countryOrRegionList = await _unitOfWork.CountryOrRegion.GetAllAsync();
            IEnumerable<City> cityList = await _unitOfWork.City.GetAllAsync();
            ProvinceVM provinceVM = new ProvinceVM()
            {
                Province = new Province(),
                CityList = cityList.Select(i => new SelectListItem
                {
                    Text = i.Name,
                    Value = i.Id.ToString()
                }),
                CountryOrRegionList = countryOrRegionList.Select(i => new SelectListItem
                {
                    Text = i.Name,
                    Value = i.Id.ToString()
                })
            };
            if (id == null)
            {
                // create
                return View(provinceVM);
            }

            // edit
            provinceVM.Province = await _unitOfWork.Province.GetAsync(id.GetValueOrDefault());
            if (provinceVM == null)
            {
                return NotFound();
            }
            return View(provinceVM);
        }

        // in MVC we can do api calls within the controllers and view actions
        #region API_CALS

        [HttpGet]
        public async Task<IActionResult> GetAll()
        {
            var provinceList = _db.Provinces.Include(p => p.CountryOrRegion).Include(p => p.City).ToList();
            var countryOrRegionList = _db.CountryOrRegions.ToList();
            var cityList = _db.Cities.ToList();
            foreach (var province in provinceList)
            {
                var countryOrRegionId = countryOrRegionList.FirstOrDefault(c => c.Id == province.CountryOrRegionId).Id;
                province.CountryOrRegion.Name = countryOrRegionList.FirstOrDefault(c => c.Id == countryOrRegionId).Name;
                if (province.CountryOrRegion == null)
                {
                    province.CountryOrRegion = new()
                    {
                        Name = ""
                    };
                }
            }
            return Json(new { data = provinceList });
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Upsert(ProvinceVM provinceVM)
        {
            if (ModelState.IsValid) // make sure validation is valid if client side is not working
            {
                if (provinceVM.Province.Id == 0)
                {
                    await _unitOfWork.Province.AddAsync(provinceVM.Province);
                }
                else
                {
                    _unitOfWork.Province.Update(provinceVM.Province);
                }
                await _unitOfWork.SaveAsync();
                return RedirectToAction(nameof(Index));
            }
            return View(provinceVM);
        }

        [HttpDelete]
        public async Task<IActionResult> Delete(int id)
        {
            var objFromDb = await _unitOfWork.Province.GetAsync(id);
            if (objFromDb == null)
            {
                return Json(new { success = false, message = "Error while deleting" });
            }
            await _unitOfWork.Province.RemoveAsync(objFromDb);
            await _unitOfWork.SaveAsync();
            return Json(new { success = true, message = "Delete Successful" });
        }

        #endregion
    }
}
