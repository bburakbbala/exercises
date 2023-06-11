using Clinic.DataAccess.Data;
using Clinic.DataAccess.Repository.IRepository;
using Clinic.Models;
using System.Linq;

namespace Clinic.DataAccess.Repository
{
    public class DepartmentRepository : RepositoryAsync<Department>, IDepartmentRepository
    {
        private readonly ApplicationDbContext _db;
        public DepartmentRepository(ApplicationDbContext db) : base(db)
        {
            _db = db;
        }

        public void Update(Department departmant)
        {
            var objFromDb = _db.Departments.FirstOrDefault(d => d.Id == departmant.Id);
            if (objFromDb != null)
            {
                objFromDb.Name = departmant.Name;
            }
        }
    }
}
