using System.ComponentModel.DataAnnotations.Schema;

namespace Clinic.Models
{
    public class TreatmentMedicine
    {
        public int Id { get; set; }

        public int? MedicineId { get; set; }
        [ForeignKey("MedicineId")]
        public Medicine Medicine { get; set; }

        public int? MedicineUsageId { get; set; }
        [ForeignKey("MedicineUsageId")]
        public MedicineUsage MedicineUsage { get; set; }
    }
}
