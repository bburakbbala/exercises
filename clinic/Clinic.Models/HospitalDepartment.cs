using System;
using System.ComponentModel.DataAnnotations.Schema;

namespace Clinic.Models
{
    public class HospitalDepartment
    {
        public int Id { get; set; }

        public Guid? HospitalId { get; set; }
        [ForeignKey("HospitalId")]
        public Hospital Hospital { get; set; }

        public int? DepartmentId { get; set; }
        [ForeignKey("DepartmentId")]
        public Department Department { get; set; }
    }
}
