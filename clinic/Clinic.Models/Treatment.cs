using System;
using System.ComponentModel.DataAnnotations.Schema;

namespace Clinic.Models
{
    public class Treatment
    {
        public int Id { get; set; }

        public DateTime Date { get; set; }

        public string AdmissionNote { get; set; }

        public bool Status { get; set; }

        public string? PatientId { get; set; }
        [ForeignKey("PatientId")]
        public Patient Patient { get; set; }
    }
}
