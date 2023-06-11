using System.ComponentModel.DataAnnotations;

namespace Clinic.Models
{
    public class CountryOrRegion
    {
        public int Id { get; set; }

        // ISO 3166 Alpha-3
        [Required]
        public string AlphaCode { get; set; }

        [Required]
        public string Name { get; set; }
    }
}