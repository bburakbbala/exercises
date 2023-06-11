using Clinic.Models;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;

namespace Clinic.DataAccess.Data
{
    public class ApplicationDbContext : IdentityDbContext
    {
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options)
            : base(options)
        {
        }

        public DbSet<BloodType> BloodTypes { get; set; }
        public DbSet<City> Cities { get; set; }
        public DbSet<CountryOrRegion> CountryOrRegions { get; set; }
        public DbSet<Department> Departments { get; set; }
        public DbSet<Doctor> Doctors { get; set; }
        public DbSet<DoctorTreatment> DoctorTreatments { get; set; }
        public DbSet<Hospital> Hospitals { get; set; }
        public DbSet<HospitalDepartment> HospitalDepartments { get; set; }
        public DbSet<HospitalDoctor> HospitalDoctors { get; set; }
        public DbSet<HospitalLab> HospitalLabs { get; set; }
        public DbSet<HospitalVisit> HospitalVisits { get; set; }
        public DbSet<Lab> Labs { get; set; }
        public DbSet<LabMachine> LabMachines { get; set; }
        public DbSet<LabMachineTest> LabMachineTests { get; set; }
        public DbSet<Medicine> Medicines { get; set; }
        public DbSet<MedicineCategory> MedicineCategories { get; set; }
        public DbSet<MedicineUsage> MedicineUsages { get; set; }
        public DbSet<Patient> Patients { get; set; }
        public DbSet<PatientMedicine> PatientMedicines { get; set; }
        public DbSet<Prescription> Prescriptions { get; set; }
        public DbSet<PrescriptionMedicine> PrescriptionMedicines { get; set; }
        public DbSet<Profession> Professions { get; set; }
        public DbSet<Province> Provinces { get; set; }
        public DbSet<Test> Tests { get; set; }
        public DbSet<TestResult> TestResults { get; set; }
        public DbSet<Treatment> Treatments { get; set; }
        public DbSet<TreatmentMedicine> TreatmentMedicines { get; set; }

    }
}
