using Clinic.DataAccess.Data;
using Clinic.DataAccess.Repository.IRepository;
using Clinic.Models;

namespace Clinic.DataAccess.Repository
{
    public class PatientRepository : RepositoryAsync<Patient>, IPatientRepository
    {
        private readonly ApplicationDbContext _db;
        public PatientRepository(ApplicationDbContext db) : base(db)
        {
            _db = db;
        }

        public void Update(Patient patient)
        {
        }
    }
}
