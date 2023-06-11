using System.ComponentModel.DataAnnotations.Schema;

namespace Clinic.Models
{
    public class TestResultTest
    {
        public int Id { get; set; }

        public string? TestId { get; set; }
        [ForeignKey("TestId")]
        public Test Test { get; set; }

        public string? TestResultId { get; set; }
        [ForeignKey("TestResultId")]
        public TestResult TestResult { get; set; }
    }
}
