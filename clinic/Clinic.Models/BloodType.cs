using System.ComponentModel.DataAnnotations;

namespace Clinic.Models
{
    public class BloodType
    {
        public short Id { get; set; }

        [Required]
        public string Type { get; set; }

        [Required]
        public string Rh { get; set; }
    }
}
