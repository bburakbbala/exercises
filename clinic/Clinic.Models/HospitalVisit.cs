using System;
using System.ComponentModel.DataAnnotations.Schema;

namespace Clinic.Models
{
    public class HospitalVisit
    {
        public int Id { get; set; }

        public DateTime Date { get; set; }

        public string? PatientId { get; set; }
        [ForeignKey("PatientId")]
        public Patient Patient { get; set; }

        public Guid? HospitalId { get; set; }
        [ForeignKey("HospitalId")]
        public Hospital Hospital { get; set; }
    }
}
