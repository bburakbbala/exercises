using System;
using System.ComponentModel.DataAnnotations.Schema;

namespace Clinic.Models
{
    public class Prescription
    {
        public Guid Id { get; set; }

        public DateTime Date { get; set; }

        private DateTime expirationData;
        public DateTime ExpirationDate
        {
            get { return expirationData; }

            private set { expirationData = Date.AddDays(3); }
        }

        public string? PatientId { get; set; }
        [ForeignKey("PatientId")]
        public Patient Patient { get; set; }

        public string? DoctorId { get; set; }
        [ForeignKey("DoctorId")]
        public Doctor Doctor { get; set; }

        public int? MedicineUsageId { get; set; }
        [ForeignKey("MedicineUsageId")]
        public MedicineUsage MedicineUsage { get; set; }
    }
}
