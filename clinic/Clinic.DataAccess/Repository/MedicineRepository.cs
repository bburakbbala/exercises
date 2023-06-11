using Clinic.DataAccess.Data;
using Clinic.DataAccess.Repository.IRepository;
using Clinic.Models;

namespace Clinic.DataAccess.Repository
{
    public class MedicineRepository : RepositoryAsync<Medicine>, IMedicineRepository
    {
        private readonly ApplicationDbContext _db;
        public MedicineRepository(ApplicationDbContext db) : base(db)
        {
            _db = db;
        }

        public void Update(Medicine medicine)
        {
        }
    }
}
