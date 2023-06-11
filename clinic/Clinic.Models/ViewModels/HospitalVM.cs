using Microsoft.AspNetCore.Mvc.Rendering;
using System.Collections.Generic;

namespace Clinic.Models.ViewModels
{
    public class HospitalVM
    {
        public Hospital Hospital { get; set; }

        public IEnumerable<SelectListItem> HospitalDepartmentList { get; set; }

        public IEnumerable<SelectListItem> HospitalLabList { get; set; }

        public IEnumerable<SelectListItem> HospitalDoctorList { get; set; }

        public IEnumerable<SelectListItem> CountryOrRegionList { get; set; }

        public IEnumerable<SelectListItem> CityList { get; set; }

        public IEnumerable<SelectListItem> ProvinceList { get; set; }
    }
}
