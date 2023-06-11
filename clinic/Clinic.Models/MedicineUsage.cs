using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Clinic.Models
{
    public class MedicineUsage
    {
        public int Id { get; set; }

        [Required]
        public int Dose { get; set; }

        [Required]
        public string TypeOfUsage { get; set; }

        [Required]
        public string Frequency { get; set; }

        [NotMapped]
        public string Instruction
        {
            get
            {
                return Frequency + " " + TypeOfUsage;
            }
        }

        public int? MedicineId { get; set; }
        [ForeignKey("MedicineId")]
        public Medicine Medicine { get; set; }
    }
}
