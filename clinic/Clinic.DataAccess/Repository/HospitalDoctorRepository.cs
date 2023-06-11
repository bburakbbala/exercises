using Clinic.DataAccess.Data;
using Clinic.DataAccess.Repository.IRepository;
using Clinic.Models;

namespace Clinic.DataAccess.Repository
{
    public class HospitalDoctorRepository : RepositoryAsync<HospitalDoctor>, IHospitalDoctorRepository
    {
        private readonly ApplicationDbContext _db;
        public HospitalDoctorRepository(ApplicationDbContext db) : base(db)
        {
            _db = db;
        }

        public void Update(HospitalDoctor hospitalDoctor)
        {

        }
    }
}
