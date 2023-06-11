using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Clinic.Models
{
    public class LabMachine
    {
        public Guid Id { get; set; }

        [Required]
        public string Name { get; set; }

        public Guid? LabId { get; set; }
        [ForeignKey("LabId")]
        public Lab Lab { get; set; }
    }
}
