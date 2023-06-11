using Clinic.DataAccess.Data;
using Clinic.DataAccess.Repository.IRepository;
using Clinic.Models;

namespace Clinic.DataAccess.Repository
{
    public class HospitalVisitRepository : RepositoryAsync<HospitalVisit>, IHospitalVisitRepository
    {
        private readonly ApplicationDbContext _db;
        public HospitalVisitRepository(ApplicationDbContext db) : base(db)
        {
            _db = db;
        }

        public void Update(HospitalVisit hospitalVisit)
        {
        }
    }
}
