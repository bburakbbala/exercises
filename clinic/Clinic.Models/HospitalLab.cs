using System;
using System.ComponentModel.DataAnnotations.Schema;

namespace Clinic.Models
{
    public class HospitalLab
    {
        public int Id { get; set; }

        public Guid? HospitalId { get; set; }
        [ForeignKey("HospitalId")]
        public Hospital Hospital { get; set; }

        public Guid? LabId { get; set; }
        [ForeignKey("LabId")]
        public Lab Lab { get; set; }
    }
}
