using System;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations.Schema;

namespace Clinic.Models
{
    public class TestResult
    {
        public int Id { get; set; }

        [DisplayName("Test Name")]
        public string Name { get; set; }

        public DateTime ResultDate { get; set; }

        public DateTime SampleDate { get; set; }

        public DateTime LabAccaptanceDate { get; set; }

        public string Note { get; set; }

        public string? PatientId { get; set; }
        [ForeignKey("PatientId")]
        public Patient Patient { get; set; }

        public Guid? LabId { get; set; }
        [ForeignKey("LabId")]
        public Lab Lab { get; set; }

        public string? DoctorId { get; set; }
        [ForeignKey("DoctorId")]
        public Doctor Doctor { get; set; }
    }
}
