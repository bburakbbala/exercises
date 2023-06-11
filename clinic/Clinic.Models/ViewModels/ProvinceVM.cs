using Microsoft.AspNetCore.Mvc.Rendering;
using System.Collections.Generic;

namespace Clinic.Models.ViewModels
{
    public class ProvinceVM
    {
        public Province Province { get; set; }

        public IEnumerable<SelectListItem> CityList { get; set; }

        public IEnumerable<SelectListItem> CountryOrRegionList { get; set; }
    }
}
