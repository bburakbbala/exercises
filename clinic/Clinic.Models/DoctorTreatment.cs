using System.ComponentModel.DataAnnotations.Schema;

namespace Clinic.Models
{
    public class DoctorTreatment
    {
        public int Id { get; set; }

        public string? DoctorId { get; set; }
        [ForeignKey("DoctorId")]
        public Doctor Doctor { get; set; }

        public int? TreatmentId { get; set; }
        [ForeignKey("TreatmentId")]
        public Treatment Treatment { get; set; }
    }
}
