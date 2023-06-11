using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Clinic.Models
{
    public class City
    {
        public int Id { get; set; }

        [Required]
        public string Name { get; set; }

        public int? CountryOrRegionId { get; set; }
        [ForeignKey("CountryOrRegionId")]
        public CountryOrRegion CountryOrRegion { get; set; }
    }
}
