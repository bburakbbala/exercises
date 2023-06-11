using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Clinic.Models
{
    public class Medicine
    {
        public int Id { get; set; }

        [Required]
        public string Name { get; set; }

        public int? MedicineCategoryId { get; set; }
        [ForeignKey("MedicineCategoryId")]
        public MedicineCategory MedicineCategory { get; set; }
    }
}
