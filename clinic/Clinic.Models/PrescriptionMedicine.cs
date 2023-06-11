using System;
using System.ComponentModel.DataAnnotations.Schema;

namespace Clinic.Models
{
    public class PrescriptionMedicine
    {
        public int Id { get; set; }

        public Guid? PrescriptionId { get; set; }
        [ForeignKey("PrescriptionId")]
        public Prescription Prescription { get; set; }

        public int? MedicineUsageId { get; set; }
        [ForeignKey("MedicineUsageId")]
        public MedicineUsage MedicineUsage { get; set; }
    }
}
