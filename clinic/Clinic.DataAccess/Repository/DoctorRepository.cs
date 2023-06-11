using Clinic.DataAccess.Data;
using Clinic.DataAccess.Repository.IRepository;
using Clinic.Models;

namespace Clinic.DataAccess.Repository
{
    public class DoctorRepository : RepositoryAsync<Doctor>, IDoctorRepository
    {
        private readonly ApplicationDbContext _db;
        public DoctorRepository(ApplicationDbContext db) : base(db)
        {
            _db = db;
        }

        public void Update(Doctor doctor)
        {
        }
    }
}
