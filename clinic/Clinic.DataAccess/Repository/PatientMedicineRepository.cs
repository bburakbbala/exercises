using Clinic.DataAccess.Data;
using Clinic.DataAccess.Repository.IRepository;
using Clinic.Models;

namespace Clinic.DataAccess.Repository
{
    public class PatientMedicineRepository : RepositoryAsync<PatientMedicine>, IPatientMedicineRepository
    {
        private readonly ApplicationDbContext _db;
        public PatientMedicineRepository(ApplicationDbContext db) : base(db)
        {
            _db = db;
        }

        public void Update(PatientMedicine patientMedicine)
        {
        }
    }
}
