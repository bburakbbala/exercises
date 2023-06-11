using System;
using System.ComponentModel.DataAnnotations.Schema;

namespace Clinic.Models
{
    public class HospitalDoctor
    {
        public int Id { get; set; }

        public Guid? HospitalId { get; set; }
        [ForeignKey("HospitalId")]
        public Hospital Hospital { get; set; }

        public string? DoctorId { get; set; }
        [ForeignKey("DoctorId")]
        public Doctor Doctor { get; set; }
    }
}
