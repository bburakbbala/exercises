using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Clinic.Models
{
    public class Province
    {
        // zip codes are not unique globally
        public int Id { get; set; }

        [Required]
        public string ZipCode { get; set; }

        [Required]
        public string Name { get; set; }

        public int? CityId { get; set; }
        [ForeignKey("CityId")]
        public City City { get; set; }

        public int? CountryOrRegionId { get; set; }
        [ForeignKey("CountryOrRegionId")]
        public CountryOrRegion CountryOrRegion { get; set; }
    }
}
