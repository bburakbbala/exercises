using Microsoft.AspNetCore.Mvc.Rendering;
using System.Collections.Generic;

namespace Clinic.Models.ViewModels
{
    public class LabVM
    {
        public Lab Lab { get; set; }

        public IEnumerable<SelectListItem> HospitalLabList { get; set; }

        public IEnumerable<SelectListItem> LabMachineList { get; set; }

        public IEnumerable<SelectListItem> TestResultList { get; set; }

        public IEnumerable<SelectListItem> CountryOrRegionList { get; set; }

        public IEnumerable<SelectListItem> CityList { get; set; }

        public IEnumerable<SelectListItem> ProvinceList { get; set; }
    }
}
