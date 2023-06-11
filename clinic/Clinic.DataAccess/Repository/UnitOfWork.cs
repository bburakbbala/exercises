using Clinic.DataAccess.Data;
using Clinic.DataAccess.Repository.IRepository;
using System;
using System.Threading.Tasks;

namespace Clinic.DataAccess.Repository
{
    public class UnitOfWork : IUnitOfWork
    {
        private readonly ApplicationDbContext _db;

        public ISP_Call SP_Call { get; private set; }

        public IBloodTypeRepository BloodType { get; private set; }
        public ICityRepository City { get; private set; }
        public ICountryOrRegionRepository CountryOrRegion { get; private set; }
        public IDepartmentRepository Department { get; private set; }
        public IDoctorRepository Doctor { get; private set; }
        public IDoctorTreatmentRepository DoctorTreatment { get; private set; }
        public IHospitalDepartmentRepository HospitalDepartment { get; private set; }
        public IHospitalDoctorRepository HospitalDoctor { get; private set; }
        public IHospitalLabRepository HospitalLab { get; private set; }
        public IHospitalRepository Hospital { get; private set; }
        public IHospitalVisitRepository HospitalVisit { get; private set; }
        public ILabMachineRepository LabMachine { get; private set; }
        public ILabRepository Lab { get; private set; }
        public IMedicineCategoryRepository MedicineCategory { get; private set; }
        public IMedicineRepository Medicine { get; private set; }
        public IMedicineUsageRepository MedicineUsage { get; private set; }
        public IPatientMedicineRepository PatientMedicine { get; private set; }
        public IPatientRepository Patient { get; private set; }
        public IPrescriptionMedicineRepository PrescriptionMedicine { get; private set; }
        public IPrescriptionRepository Prescription { get; private set; }
        public IProfessionRepository Profession { get; private set; }
        public IProvinceRepository Province { get; private set; }
        public ITestRepository Test { get; private set; }
        public ITestResultRepository TestResult { get; private set; }
        public ITestResultTestRepository TestResultTest { get; private set; }
        public ITreatmentMedicineRepository TreatmentMedicine { get; private set; }
        public ITreatmentRepository Treatment { get; private set; }

        public UnitOfWork(ApplicationDbContext db)
        {
            _db = db;
            SP_Call = new SP_Call(_db);
            BloodType = new BloodTypeRepository(_db);
            City = new CityRepository(_db);
            CountryOrRegion = new CountryOrRegionRepository(db);
            Department = new DepartmentRepository(_db);
            Doctor = new DoctorRepository(_db);
            HospitalDepartment = new HospitalDepartmentRepository(_db);
            HospitalDoctor = new HospitalDoctorRepository(_db);
            HospitalLab = new HospitalLabRepository(_db);
            Hospital = new HospitalRepository(_db);
            HospitalVisit = new HospitalVisitRepository(_db);
            LabMachine = new LabMachineRepository(_db);
            Lab = new LabRepository(_db);
            MedicineCategory = new MedicineCategoryRepository(_db);
            Medicine = new MedicineRepository(_db);
            MedicineUsage = new MedicineUsageRepository(_db);
            PatientMedicine = new PatientMedicineRepository(_db);
            Patient = new PatientRepository(_db);
            PrescriptionMedicine = new PrescriptionMedicineRepository(_db);
            Prescription = new PrescriptionRepository(_db);
            Profession = new ProfessionRepository(_db);
            Province = new ProvinceRepository(_db);
            Test = new TestRepository(_db);
            TestResult = new TestResultRepository(_db);
            TestResultTest = new TestResultTestRepository(_db);
            TreatmentMedicine = new TreatmentMedicineRepository(_db);
            Treatment = new TreatmentRepository(_db);
        }

        public void Dispose()
        {
            _db.Dispose();
            GC.SuppressFinalize(this);
        }

        public async Task SaveAsync()
        {
            await _db.SaveChangesAsync(); // in repository we are not saving any changes that we made on db
        }
    }
}
