using Clinic.DataAccess.Data;
using Clinic.DataAccess.Repository.IRepository;
using Clinic.Models;

namespace Clinic.DataAccess.Repository
{
    public class TreatmentRepository : RepositoryAsync<Treatment>, ITreatmentRepository
    {
        private readonly ApplicationDbContext _db;
        public TreatmentRepository(ApplicationDbContext db) : base(db)
        {
            _db = db;
        }

        public void Update(Treatment treatment)
        {
        }
    }
}
