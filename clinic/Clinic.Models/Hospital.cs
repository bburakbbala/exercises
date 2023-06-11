using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Clinic.Models
{
    public class Hospital
    {
        public Guid Id { get; set; }

        public string ImageUrl { get; set; }

        [Required]
        [Display(Name = "Hospital Name")]
        public string Name { get; set; }

        [Required]
        public string Description { get; set; }

        [Required]
        public string AddressDetail { get; set; }

        public int? CountryOrRegionId { get; set; }
        [ForeignKey("CountryOrRegionId")]
        public CountryOrRegion CountryOrRegion { get; set; }

        public int? CityId { get; set; }
        [ForeignKey("CityId")]
        public Province City { get; set; }

        public int? ProvinceId { get; set; }
        [ForeignKey("ProvinceId")]
        public Province Province { get; set; }
    }
}
