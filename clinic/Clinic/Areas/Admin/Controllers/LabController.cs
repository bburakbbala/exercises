using Clinic.DataAccess.Repository.IRepository;
using Clinic.Models;
using Clinic.Models.ViewModels;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Clinic.Areas.Admin.Controllers
{
    [Area("Admin")]
    public class LabController : Controller
    {
        private readonly IUnitOfWork _unitOfWork;

        public LabController(IUnitOfWork unitOfWork)
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
            IEnumerable<City> cityList = await _unitOfWork.City.GetAllAsync();
            IEnumerable<Province> provinceList = await _unitOfWork.Province.GetAllAsync();
            IEnumerable<HospitalLab> hospitalLabList = await _unitOfWork.HospitalLab.GetAllAsync();
            IEnumerable<LabMachine> labMachineList = await _unitOfWork.LabMachine.GetAllAsync();
            IEnumerable<TestResult> testResultList = await _unitOfWork.TestResult.GetAllAsync();
            LabVM labVM = new()
            {
                Lab = new Lab(),
                CountryOrRegionList = countryOrRegionList.Select(i => new SelectListItem
                {
                    Text = i.Name,
                    Value = i.Id.ToString()
                }),
                CityList = cityList.Select(i => new SelectListItem
                {
                    Text = i.Name,
                    Value = i.Id.ToString()
                }),
                ProvinceList = provinceList.Select(i => new SelectListItem
                {
                    Text = i.Name,
                    Value = i.Id.ToString()
                })
            };
            if (id == null)
            {
                // create
                return View(labVM);
            }

            // edit
            labVM.Lab = await _unitOfWork.Lab.GetAsync(id.GetValueOrDefault());
            if (labVM.Lab == null)
            {
                return NotFound();
            }
            return View(labVM);
        }

        #region API_CALS

        [HttpGet]
        public async Task<IActionResult> GetAll()
        {
            var allObj = await _unitOfWork.Lab.GetAllAsync();
            return Json(new { data = allObj });
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Upsert(LabVM labVm)
        {
            if (ModelState.IsValid) // make sure validation is valid if client side is not working
            {
                if (labVm.Lab.Id.Equals(""))
                {
                    await _unitOfWork.Lab.AddAsync(labVm.Lab);
                }
                else
                {
                    _unitOfWork.Lab.Update(labVm.Lab);
                }
                await _unitOfWork.SaveAsync();
                return RedirectToAction(nameof(Index));
            }
            return View(labVm);
        }

        [HttpDelete]
        public async Task<IActionResult> Delete(Guid id)
        {
            var objFromDb = await _unitOfWork.Lab.GetAsync(id);
            if (objFromDb == null)
            {
                return Json(new { success = false, message = "Error while deleting" });
            }
            await _unitOfWork.Lab.RemoveAsync(objFromDb);
            await _unitOfWork.SaveAsync();
            return Json(new { success = true, message = "Delete Successful" });
        }

        #endregion
    }
}
