using Microsoft.AspNetCore.Identity;
using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Clinic.Models
{
    public class Patient : IdentityUser
    {
        [Required]
        public string Identity { get; set; }

        [Required]
        public string Firstname { get; set; }

        [Required]
        public string Lastname { get; set; }

        [Required]
        public DateTime BirthDate { get; set; }

        [Required]
        public Gender Gender { get; set; }

        public string BloodType { get; set; }

        public string MotherName { get; set; }

        public string FatherName { get; set; }

        public int? ProfessionId { get; set; }
        [ForeignKey("ProfessionId")]
        public Profession Profession { get; set; }

        public string AddressDetail { get; set; }

        public int? CountryOrRegionId { get; set; }
        [ForeignKey("CountryOrRegionId")]
        public CountryOrRegion CountryOrRegion { get; set; }

        public int? CityId { get; set; }
        [ForeignKey("CityId")]
        public Province City { get; set; }

        public int? ProvinceId { get; set; }
        [ForeignKey("ProvinceId")]
        public Province Province { get; set; }

        [NotMapped]
        public string GetFirstnameLastname => Firstname + " " + Lastname;

        [NotMapped]
        public int GetAge => DateTime.Now.Year - BirthDate.Date.Year;

        [NotMapped]
        public string Role { get; set; }

    }
}
