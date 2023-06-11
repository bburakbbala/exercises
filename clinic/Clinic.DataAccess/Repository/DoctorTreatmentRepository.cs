using Clinic.DataAccess.Data;
using Clinic.DataAccess.Repository.IRepository;
using Clinic.Models;

namespace Clinic.DataAccess.Repository
{
    public class DoctorTreatmentRepository : RepositoryAsync<DoctorTreatment>, IDoctorTreatmentRepository
    {
        private readonly ApplicationDbContext _db;
        public DoctorTreatmentRepository(ApplicationDbContext db) : base(db)
        {
            _db = db;
        }

        public void Update(DoctorTreatment doctorTreatment)
        {
        }
    }
}
