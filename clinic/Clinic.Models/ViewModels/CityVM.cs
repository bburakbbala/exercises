using Microsoft.AspNetCore.Mvc.Rendering;
using System.Collections.Generic;

namespace Clinic.Models.ViewModels
{
    public class CityVM
    {
        public City City { get; set; }

        public IEnumerable<SelectListItem> CountryOrRegionList { get; set; }
    }
}
