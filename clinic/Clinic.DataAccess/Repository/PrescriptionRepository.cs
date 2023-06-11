using Clinic.DataAccess.Data;
using Clinic.DataAccess.Repository.IRepository;
using Clinic.Models;

namespace Clinic.DataAccess.Repository
{
    public class PrescriptionRepository : RepositoryAsync<Prescription>, IPrescriptionRepository
    {
        private readonly ApplicationDbContext _db;
        public PrescriptionRepository(ApplicationDbContext db) : base(db)
        {
            _db = db;
        }

        public void Update(Prescription prescription)
        {
        }
    }
}
