using Clinic.DataAccess.Data;
using Clinic.DataAccess.Repository.IRepository;
using Clinic.Models;

namespace Clinic.DataAccess.Repository
{
    public class HospitalLabRepository : RepositoryAsync<HospitalLab>, IHospitalLabRepository
    {
        private readonly ApplicationDbContext _db;
        public HospitalLabRepository(ApplicationDbContext db) : base(db)
        {
            _db = db;
        }

        public void Update(HospitalLab hospitalLab)
        {
        }
    }
}
